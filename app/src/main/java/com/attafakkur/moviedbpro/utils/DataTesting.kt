package com.attafakkur.moviedbpro.utils

import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity

object DataTesting {

    fun movieDummy(): ArrayList<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()
        movies.add(
            MoviesEntity(
                "460465",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return movies
    }

    fun tvDummy(): ArrayList<TvShowsEntity> {
        val tvShows = ArrayList<TvShowsEntity>()
        tvShows.add(
            TvShowsEntity(
                "95557",
                "2021-03-26",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet.Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’ s tutelage.",
                "en",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                "8.9"
            )
        )
        return tvShows
    }

    fun trendDummy(): ArrayList<TrendEntity> {
        val trend = ArrayList<TrendEntity>()
        trend.add(
            TrendEntity(
                "581389",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "Space Sweepers",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "2021-02-05",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "English",
                "6.0"
            )
        )
        return trend
    }

    fun dummySearch(): ArrayList<SearchEntity> {
        val search = ArrayList<SearchEntity>()
        search.add(
            SearchEntity(
                "460465",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return search
    }

    fun detailMovieDummy(movieId: String): ArrayList<MoviesEntity> {
        val detailMovie = ArrayList<MoviesEntity>()
        detailMovie.add(
            MoviesEntity(
                movieId,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return detailMovie
    }

    fun detailTvDummy(tvId: String): ArrayList<TvShowsEntity> {
        val detailTvShow = ArrayList<TvShowsEntity>()
        detailTvShow.add(
            TvShowsEntity(
                tvId,
                "2021-03-26",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "en",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                "8.9"
            )
        )
        return detailTvShow
    }

    fun detailTrendDummy(trendId: String): ArrayList<TrendEntity> {
        val trend = ArrayList<TrendEntity>()
        trend.add(
            TrendEntity(
                trendId,
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "English",
                "Space Sweepers",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "2021-02-05",
                "6.0"
            )
        )
        return trend
    }

    // remote
    fun remoteMovieDummy(): ArrayList<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()
        movies.add(
            MoviesEntity(
                "460465",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return movies
    }

    fun remoteTvDummy(): ArrayList<TvShowsEntity> {
        val tvShows = ArrayList<TvShowsEntity>()
        tvShows.add(
            TvShowsEntity(
                "95557",
                "2021-03-26",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet.Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’ s tutelage.",
                "en",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                "8.9"
            )
        )
        return tvShows
    }

    fun remoteDetailMovieDummy(movieId: String): ArrayList<MoviesEntity> {
        val detailMovie = ArrayList<MoviesEntity>()
        detailMovie.add(
            MoviesEntity(
                movieId,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return detailMovie
    }

    fun remoteDetailTvDummy(tvId: String): ArrayList<TvShowsEntity> {
        val detailTvShow = ArrayList<TvShowsEntity>()
        detailTvShow.add(
            TvShowsEntity(
                tvId,
                "2021-03-26",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "en",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Invincible",
                "8.9"
            )
        )
        return detailTvShow
    }

    fun remoteTrendDummy(): ArrayList<TrendEntity> {
        val trend = ArrayList<TrendEntity>()
        trend.add(
            TrendEntity(
                "581389",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "English",
                "Space Sweepers",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "2021-02-05",
                "6.0"
            )
        )
        return trend
    }

    fun remoteDetailTrendDummy(trendId: String): ArrayList<TrendEntity> {
        val trend = ArrayList<TrendEntity>()
        trend.add(
            TrendEntity(
                trendId,
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "English",
                "Space Sweepers",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
                "2021-02-05",
                "6.0"
            )
        )
        return trend
    }

    fun remoteSearchDummy(): ArrayList<SearchEntity> {
        val movies = ArrayList<SearchEntity>()
        movies.add(
            SearchEntity(
                "460465",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return movies
    }

    fun remoteDetailSearchDummy(search_id: String): ArrayList<SearchEntity> {
        val search = ArrayList<SearchEntity>()
        search.add(
            SearchEntity(
                search_id,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "7.8"
            )
        )
        return search
    }
}