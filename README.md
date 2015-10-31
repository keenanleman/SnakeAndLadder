# Snake And Ladder Scala Team A 

## Untuk Kontributor

Ini kelas-kelas yang sudah ada:

* Kelas GameCanvas: Display untuk game, menggunakan kelas _Canvas_ dan _JFrame_
* Kelas GameEngine: Isinya: GameLoop untuk pengaturan FPS, Mekanisme rendering dan update
* Object Launcher: Kelas Tester

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
