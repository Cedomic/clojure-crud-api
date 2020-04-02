# Try A New Language Series: CLOJURE

In the `Try A New Language Series` I take a new programming language that looks promising and implement a simple CRUD API in it.

In this part I took the programming language `Clojure`.

The API can store artists and their songs in a database (Postgres) and lets you perform basic CRUD operations.

## Usage

You will need `Clojure` & `Leiningen` on your machine.

To start the server run the following commands:

    lein deps
    lein run

Now you can visit [`localhost:3000`](http://localhost:3000) from your browser.

## API

- /artists

        GET => Get all artists
            Response => [
                {
                    "description": "This is a description",
                    "id": 1,
                    "name": "Example Artist",
                    "created_at": "2020-04-02T11:46:32Z"
                }
            ]

        POST => Create a new artist
            Body => {
                "name": "Example Artist",
                "description": "This is a description"
            }

            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example Artist",
                "created_at": "2020-04-02T11:46:32Z"
            }


- /artists/{artistId}

        GET => Get an artist by id
            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example Artist",
                "created_at": "2020-04-02T11:46:32Z"
            }
            

        PUT => Update an artist
            Body => {
                "name": "Updated Example Artist",
                "description": "This is a description"
            }

            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example Artist",
                "created_at": "2020-04-02T11:46:32Z"
            }
        
        DELETE => Delete an artist by id
    
- /songs/{songId}

        GET => Get a song by songId
            Response => {
				"artist_id": 1,
				"genre": [
					"Indie Poptism"
				],
				"id": 1,
				"length": 180,
				"title": "good morning",
                "created_at": "2020-04-02T11:46:32Z"
			}
        
        PUT => Update a song by songId
            Body => {
                "title": "updated song title",
                "genre": ["Indie Poptism"],
                "length": 180
            }

            Response => {
                "title": "updated song title",
                "genre": ["Indie Poptism"],
                "length": 180,
                "created_at": "2020-04-02T11:46:32Z"
            }
        
        DELETE => Delete a song by songId

- /songs/artist/{artistId}

        GET => Get all songs from an artist by id
            Response => [
				{
					"artist_id": 1,
					"genre": [
						"Indie Poptism"
					],
					"id": 1,
					"length": 180,
					"title": "good morning",
                    "created_at": "2020-04-02T11:46:32Z"
				}
            ]


