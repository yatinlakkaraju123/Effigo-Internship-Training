import { BrowserRouter,Routes,Route,Navigate } from "react-router-dom"
import Home from "./components/Home"
import AuthProvider, { useAuth } from "./components/security/Auth";
import LoginPage from "./components/security/Login";
import LogoutPage from "./components/security/Logout";
import Profile from "./components/Profile";
import Category from "./components/Category";
import CategoryComponent from "./components/CategoryComponent";
import ManageExpenses from "./components/ManageExpenses"
import AddExpenses from "./components/AddExpenses";
import RegisterPage from "./components/security/RegisterPage";
import { ToastContainer } from "react-toastify";
function AuthenticatedRoute({ children}:any) {
  const auth = useAuth();

  if (!auth.isAuthenticated) {
      return <Navigate to="/login" />;
  }

  

  return children;
}
function App() {

  return (
    <>
    <AuthProvider>
      <ToastContainer/>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<AuthenticatedRoute><Home/></AuthenticatedRoute>}></Route>
      <Route path="/profile" element={<AuthenticatedRoute><Profile/></AuthenticatedRoute>}></Route>
      <Route path="/Category" element={<AuthenticatedRoute><Category/></AuthenticatedRoute>}></Route>
      <Route path="/category/:id" element={<AuthenticatedRoute><CategoryComponent/></AuthenticatedRoute>}></Route>
      <Route path="/AddExpenses" element={<AuthenticatedRoute><AddExpenses/></AuthenticatedRoute>}></Route>
      <Route path="/ManageExpenses" element={<AuthenticatedRoute><ManageExpenses/></AuthenticatedRoute>}></Route>
      <Route path="/login" element={<LoginPage/>}></Route>
      <Route path="/logout" element={<LogoutPage/>}></Route>
      <Route path="/register" element={<RegisterPage/>}></Route>

    </Routes>
    </BrowserRouter>
    </AuthProvider>
    </>
  )
}

export default App
