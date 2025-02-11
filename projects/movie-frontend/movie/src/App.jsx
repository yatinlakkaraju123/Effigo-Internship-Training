import { useState,useEffect } from "react";
import Search from "./components/Search";
import MovieCards from "./components/MovieCards";
import Spinner from "./components/Spinner"
import {useDebounce} from "react-use"
import { getTrendingMovies, updateSearchCount } from "./appwrite";
const API_BASE_URL="https://api.themoviedb.org/3/";
const API_KEY=import.meta.env.VITE_TMDB_API_KEY
const API_OPTIONS={
  method:'GET',
  headers:{
    accept:'application/json',
    Authorization:`Bearer ${API_KEY}`
  }
}
const App = ()=>{
  const [searchTerm,setSearchTerm] = useState('')
  const [isTrendingLoading,setIsTrendingLoading] = useState(false)
  const [trendingErrorMessage,setTrendingErrorMessage] = useState("")
  const [errorMessage,setErrorMessage] = useState("")
  const [movieList,setMovieList] = useState([])
  const [isLoading,setIsLoading] = useState(false)
  const [debouncedSearchTerm,setDebouncedSearchTerm] = useState("")
  const [trendingMovies,setTrendingMovies] = useState([])
  useDebounce(()=>{
setDebouncedSearchTerm(searchTerm)
  },500,[searchTerm])
  const fetchMovies = async(query='')=>{
    setIsLoading(true)
    setErrorMessage("")
    try {
      const endpoint = query? `${API_BASE_URL}search/movie?query=${encodeURIComponent(query)}`
      :`${API_BASE_URL}/discover/movie?sort_by=popularity.desc`
      const response = await fetch(endpoint,API_OPTIONS)
      if(!response.ok){
        throw new Error("failed to fetch movies")
      }
      const data = await response.json()
      if(data.Response==='False'){
        setErrorMessage(data.Error||"failed to fetch movies")
        setMovieList([])
        return;
      }
      setMovieList(data.results || [])
      if(query && data.results.length>0){
        await updateSearchCount(query,data.results[0])
      }
    } catch (error) {
      console.log(`Error fetching movies ${error}`)
      setErrorMessage("Error fetching movies. Please try again later")
    }finally{
      setIsLoading(false)
    }
  }
  const loadTrendingMovies = async()=>{
    try {
      const movies = await getTrendingMovies()
      setTrendingMovies(movies)
    } catch (error) {
      console.log("Error fetching trending movies",error)
      setTrendingErrorMessage("Error fetching trending movies")
    }
  }
  useEffect(()=>{
fetchMovies(debouncedSearchTerm)
  },[debouncedSearchTerm])
  useEffect(()=>{
loadTrendingMovies()
  },[])
  return (
    <main>
      <div className="pattern"/>
      <div className="wrapper">
        <header>
          <img src="./hero.png" alt="Hero Banner"/>
          <h1>Find <span className="text-gradient"> Movies</span> You'll enjoy without hassle</h1>
          <Search searchTerm={searchTerm} setSearchTerm={setSearchTerm}/>

        </header>
        
         
         <section className="trending">
            <h2>Trending Movies</h2>
            {isTrendingLoading ? (
          <p className="text-white"><Spinner/></p>
          
        ):trendingErrorMessage ? (
          <p className="text-red-500">{trendingErrorMessage}</p>
        ):(
          <ul>
   <ul>
              {trendingMovies.map((movie,index)=>(
                <li key={movie.$id}>
                  <p>{index+1}</p>
                  <img src={movie.poster_url} alt={movie.title}/>
                </li>
              ))}
            </ul>
          </ul>
        )}
         </section> 
      
        <section className="all-movies">
        <h2>All movies</h2>
        {isLoading ? (
          <p className="text-white"><Spinner/></p>
          
        ):errorMessage ? (
          <p className="text-red-500">{errorMessage}</p>
        ):(
          <ul>
            {movieList.map((movie)=>(
              <MovieCards key={movie.id} movie={movie}/>
))}
          </ul>
        )}
        </section>

      </div>
    </main>
  );
}

export default App
