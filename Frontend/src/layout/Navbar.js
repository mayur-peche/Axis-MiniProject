import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import logo from '../logo.png'
import './Navbar.css'

export default function Navbar() {
  const navigate = useNavigate()

  const handleAnimeDatabaseClick = () => {
    navigate('/')
  }

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-custom">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            <img src={logo} alt="Logo" height="30" />
          </a>

          <div className="ml-auto">
            <button className="btn btn-outline-custom animedb-btn " onClick={handleAnimeDatabaseClick}>
              Anime Database
            </button>
            <Link className="btn btn-outline-custom animedb-btn" to="/addAnime">Add New anime</Link>
            <button className="btn btn-outline-custom other-btn" disabled>User Anime</button>
          </div>

          <div className="mr-auto">
            <button className="btn btn-outline-custom other-btn" disabled>Watching</button>
            <button className="btn btn-outline-custom other-btn" disabled>Completed</button>
            <button className="btn btn-outline-custom other-btn" disabled>Plan to Watch</button>
            <button className="btn btn-outline-custom other-btn" disabled>Dropped</button>
          </div>
        </div>
      </nav>
    </div>
  )
}
