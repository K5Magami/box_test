## gradleコマンド一覧

- ローカル環境内でjarとして実行  
    `gradle bootRun`
- 実行Jarの生成  
    `gradle build`
- eclipseプロジェクトの作成  
    `gradle eclipse`
- intelliJ IDEAプロジェクトの作成  
    `gradle idea`
- 本プロジェクトにおけるgradleのコマンド一覧の表示  
    `gradle tasks`
    
## dockerコマンド一覧
プロジェクトのルートディレクトリで実行する

- イメージの作成  
    `docker build -t box-api:0.1 .`
-  ポート8080指定でコンテナの実行  
    `docker run -p 8080:80 --name box-api box-api:0.1 .`
- コンテナの停止    
    `docker stop box-api`
- 
    ``
- 
    ``