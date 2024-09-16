import logo from './AIT.PNG'
import './login.css'
import React, { useState} from 'react'
import {StrictMode} from 'react';
import {createRoot} from 'react-dom/client';

import App from './App'

root.render(
    <StrictMode>
      <App />
    </StrictMode>,
  );

function Login(){
return(
<div className='login'>
    <img src={logo} width={200} height={200} />
</div>
);
}
export default Login;