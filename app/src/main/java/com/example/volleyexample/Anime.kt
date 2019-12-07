package com.example.volleyexample

class Anime {
    var name: String? = null
    var description: String? = null
    var rating: String? = null
    var nb_episode = 0
    var categorie: String? = null
    var studio: String? = null
    var image_url: String? = null

    constructor() {}
    constructor(
        name: String?,
        description: String?,
        rating: String?,
        nb_episode: Int,
        categorie: String?,
        studio: String?,
        image_url: String?
    ) {
        this.name = name
        this.description = description
        this.rating = rating
        this.nb_episode = nb_episode
        this.categorie = categorie
        this.studio = studio
        this.image_url = image_url
    }

}