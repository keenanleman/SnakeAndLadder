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
* Object Launcher: Kelas Tester

### Kelas-kelas yang belum ada (TODO):

* Kelas Ladder: Representasi dari tangga
* Kelas GameState: Untuk me-manage state dari game
* Kelas GameStage: Untuk me-manage jalannya permainan
* Kelas GameObjectManager: Untuk me-manage GameObject dalam sebuah GameState atau GameObject lain
* Kelas AssetManager: Untuk me-manage resource(ex: gambar, musik,etc)

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
