import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './home.css';
import { Link, useParams } from 'react-router-dom';

export default function Home() {
  const [anime, setAnime] = useState([]);

  const {id}=useParams()
  
  useEffect(() => {
    loadAnime();
  }, []);

  const loadAnime = async () => {
    const result = await axios.get('http://localhost:8094/anime/animedb');
    setAnime(result.data);
  };

  const deleteAnime = async (id) =>{
   await axios.delete(`http://localhost:8094/anime/animedb/${id}`)
   loadAnime()
  }

  return (
    <div className='container'>
      <div className='py-4'>
        <table className='table border shadow'>
          <thead>
            <tr>
              <th scope='col'>AnimeId</th>
              <th scope='col'>Action</th>
              <th scope='col'>Anime Name</th>
              <th scope='col'>Ranking</th>
              <th scope='col'>Ratings</th>
              <th scope='col'>Episodes</th>
            </tr>
          </thead>
          <tbody>
            {anime.map((animeIn, index) => (
              <tr key={animeIn.id}>
                <td>{animeIn.id}</td>
                <td>
                  <Link
                    className="btn btn-success btn-sm mx-2 rounded-pill"
                    to={`/editAnime/${animeIn.id}`}
                    >Edit </Link>
                  <Link
                    className='btn btn-danger btn-sm mx-2 rounded-pill'
                    onClick={()=> deleteAnime(animeIn.id)}
                  >Delete
                  </Link>
                </td>
                <td>{animeIn.animeName}</td>
                <td>{animeIn.ranking}</td>
                <td>{animeIn.globalRatings}</td>
                <td>{animeIn.totalEpisodes}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
