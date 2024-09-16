import { Route } from "react-router-dom";
import {Home} from "./home";
import { Login } from "./login";

function App() {

  return (
  <>

      <Routes>
        <Route path='login' element={Login} >Login</Route>
      <Route path="Home" element={Home}></Route>
      </Routes>
  
    </>
  );
}

export default App;
