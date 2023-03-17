import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router,Routes, Route } from 'react-router-dom'
import AddAnime from './apiService/AddAnime';
import EditAnime from './apiService/EditAnime';

function App() {
  return (
    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route exact path="/addAnime" element={<AddAnime/>} />
        <Route exact path="/editAnime/:id" element={<EditAnime/>} />    
      </Routes>
      </Router>
    
    </div>
  );
}

export default App;
