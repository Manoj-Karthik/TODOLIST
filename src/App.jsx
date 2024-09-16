import {Routes,Route} from 'react-router-dom'

import NavBar from './Components/NavBar'
import AddUser from './Components/AddUser'
import AddTask from './Components/AddTask'
import DeleteTask from './Components/DeleteTask'

function App() {


  return (
    <>
    <NavBar/>
    <Routes>
      <Route path='/adduser' element={<AddUser/>}/>
      <Route path='/addtask' element={<AddTask/>}/>
      <Route path='/deletetask' element={<DeleteTask/>}/>
    </Routes>
    </>
  )
}

export default App
