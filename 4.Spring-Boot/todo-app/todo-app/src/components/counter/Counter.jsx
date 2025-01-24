import React, { useState } from 'react'
import CounterButton from './CounterButton'
import "./counter.css"
function Counter() {
    const [count,setCount] = useState(0)
    function incrementMethod(num)
    {
        setCount(count+num)
    }
    function decrementMethod(num)
    {
        setCount(count-num)
    }
    function ResetMethod()
    {
        setCount(0)
    }
  return (
    <div>
        <span className='count'>{count}</span><br></br>
      <CounterButton by={1} incrementMethod={incrementMethod} decrementMethod={decrementMethod}/>
      <CounterButton by={2} incrementMethod={incrementMethod} decrementMethod={decrementMethod}/>
      <CounterButton by={5} incrementMethod={incrementMethod} decrementMethod={decrementMethod}/>
      <button className='resetButton' onClick={ResetMethod}>Reset</button>
      
    </div>
  )
}

export default Counter
