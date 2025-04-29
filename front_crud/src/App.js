import './App.css';
import Header from './pages/header/Header'; 
import { Route, Routes } from 'react-router-dom';
import Dashboard from './pages/dashboard/Dashboard';
import NoMatch from './pages/noMatch/NoMatch';
import PostAnime from './pages/animes/PostAnime';
import UpdateAnime from './pages/animes/UpdateAnime';


function App() {
  
  return (
    <>
      <Header />
      <Routes>
        <Route path ='/' element={<Dashboard/>}  />
        <Route path ='/animes' element={<PostAnime/>}  />
        <Route path ='/animes/:id' element={<UpdateAnime/>}  />
        <Route path ='*' element={<NoMatch/>}  />
      </Routes>
    </>
  );
}

export default App;
