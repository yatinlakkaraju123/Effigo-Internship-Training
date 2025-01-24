import React, { useState } from 'react'
import "./counter.css"
function CounterButton({by,incrementMethod,decrementMethod}) {
  const [count,setCount] = useState(0)
  function incrementCounterButton(){
setCount(count+Number(by));
  }
  function decrementCounterButton(){
    setCount(count-Number(by));
  }
  return (
    <div className='Counter'>
      
      <button className='counterButton' type='submit' onClick={()=>incrementMethod(by)}>+{by}</button>
      <button className='counterButton' type='submit' onClick={()=>decrementMethod(by)}>-{by}</button>

    </div>
  )
}

export default CounterButton
