import logo from './AIT.PNG'

function Home() {

    return (
    <>
      <div className="Home">
     
         <img src={logo} width={400} height={400} />
        <div>Welcome</div>
        <Routes>
          <Route path='login' element={Login} >Login</Route>
        </Routes>
      </div>
      </>
    );
  }
  
  export default Home;