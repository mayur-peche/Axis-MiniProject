import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './AddAnime.css';

export default function AddAnime() {

    let navigate=useNavigate()
  const [anime, setAnime] = useState({
    animeName: '',
    ranking: '',
    globalRatings: '',
    totalEpisodes: ''
  });

  const { animeName, ranking, globalRatings, totalEpisodes } = anime;

  const onInputChange = (e) => {
    setAnime({ ...anime, [e.target.name]: e.target.value });
  };

  const onSubmit = async(e) => {
    e.preventDefault();
    await axios.post("http://localhost:8094/anime/animedb",anime)
    navigate("/")
  };

  return (
    <div className='container'>
      <div className='row'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
          <h2 className='text-center m-4 small-title'>Add a new Anime to MAL Database</h2>
          <form onSubmit={(e)=>onSubmit(e)}>

            <div className='form-group'>
              <label htmlFor='AnimeName' className='form-label'>
                Anime Name
              </label>
              <input
                type={'text'}
                className='form-control'
                placeholder='Enter new Anime Name'
                name='animeName'
                value={animeName}
                onChange={onInputChange}
              />
            </div>
            <div className='form-group'>
              <label htmlFor='AnimeRanking' className='form-label'>
                Anime Ranking
              </label>
              <input
                type={'number'}
                className='form-control'
                placeholder='Enter Anime Ranking'
                name='ranking'
                value={ranking}
                onChange={onInputChange}
              />
            </div>
            <div className='form-group'>
              <label htmlFor='AnimeRating' className='form-label'>
                Anime Rating
              </label>
              <input
                type={'number'}
                className='form-control'
                placeholder='Enter Server Rating for the Anime (1.0-10.0)'
                name='globalRatings'
                step={0.01}
                min={1.0}
                max={10.0}
                value={globalRatings}
                onChange={onInputChange}
              />
            </div>
            <div className='form-group'>
              <label htmlFor='TotalEpisodes' className='form-label'>
                Total Episodes
              </label>
              <input
                type={'number'}
                className='form-control'
                placeholder='Enter total episodes in the anime'
                name='totalEpisodes'
                value={totalEpisodes}
                onChange={onInputChange}
              />
            </div>
            <button type='submit' className='submit btn-outline-primary mt-2'>
              Submit
            </button>
            <Link className='submit btn-outline-danger mx-2 mt-2' to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
