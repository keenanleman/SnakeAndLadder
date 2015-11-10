# Snake And Ladder Scala Team A 

## Untuk Kontributor

### Ini kelas-kelas yang sudah ada:

* Kelas GameCanvas: Display untuk game, menggunakan kelas _Canvas_ dan _JFrame_
* Kelas GameEngine: Isinya: GameLoop untuk pengaturan FPS, Mekanisme rendering dan update
* Kelas GameObject: Semua benda didalam game merupakan turunan dari kelas ini
* Kelas Tile: Representasi dari kotak-kotak didalam papan permainan
* Kelas Board: Representasi dari papan permainan, mempopulasi tile-tile yang dibutuhkan untuk membuat papan permainan
* Kelas Player: Representasi dari pemain
* Kelas Snake : Representasi dari ular
* Kelas Dadu: Representasi dari dadu
* Trait GameObjectEvent: Interface/Trait bagi GameObject yang memiliki event/update saat runtime
* PointToPointIterator: Iterator yang dapat digunakan untuk mempermudah pembuatan ular(sudah) dan tangga(belum)
* Kelas GameEvent: Menjadi event dalam game
* Trait GameEventBroadcaster: Menjadi sumber event(broadcaster) dalam game
* Trait GameEventListener: Menjadi penerima event(listener) dalam game
* Object Launcher: Kelas Tester

### Kelas-kelas yang belum ada (TODO)

* Kelas Ladder: Representasi dari tangga
* Kelas GameState: Untuk me-manage state dari game
* Kelas GameStage: Untuk me-manage jalannya permainan, bisa digabung dengan board
* Kelas GameStateManager: Untuk me-manage GameState
* Kelas AssetManager: Untuk me-manage resource(ex: gambar, musik,etc)
* ___Kalau ada yang kurang bisa ditambah disini___

### Kontributor

Jika ada pertanyaan mengenai source code yang sudah dibuat bisa di tanya langsung ke kontributornya

* Barsya: Dadu 
* Mirza: Dadu
* Samuel: GameState(belum)
* Marcho: GameStage(belum)
* Rendra: Ladder(belum)

### Kelas-kelas yang belum punya kontributor

* Kelas GameStateManager
* Kelas AssetManager
* ___Kalau ada yang kurang bisa ditambah disini___

### Diagram Program (Outdated)

![Diagram Program](Plan.jpg)

### Mekanisme

    GameDisplay ----> Menyediakan Media Untuk ----> GameEngine
                                                          |
                                                          |
                                                          |
                                                          +
    KomponenGame Lain                               GameEngine melakukan Rendering
    Membutuhkan update -----> Dilakukan oleh --->   dan Updating
    dan render method
           |
           |
           |
           +
       Diturunkan dari
           |
           |
           |
           +
        GameObject
        
        
### Relasi Board, Tile, dan GameEngine
   
    GameEngine -----> me-render -----> Board
                                         |
                                         |
                                         |
                                         +
     Masing-masing tile <------- Me-render tile-tile sebagai elemen
     me-render dirinya           pembentuk board
     sendiri
