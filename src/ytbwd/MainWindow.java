package ytbwd;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainWindow extends Application {



	@Override
	public void start (Stage primaryStage) {

		//ディレクトリ内は全て動画ファイルである前提で処理を進める
		//サブディレクトリや動画以外のファイルがあると落ちると思われ
		String path = "C:\\Users\\ht_2\\Videos\\QUEEN\\";
	    String content_Url = "<iframe width=\"400\" height=\"300\" "
	    		+ "src=\"http://www.youtube.com/embed/A22oy8dFjqc\" "
	    		+ "frameborder=\"0\" allowfullscreen></iframe>";
		File dir = new File(path);
		int fileNum = dir.listFiles().length;
		//String file_names[] = new String[fileNum];

        try {
            // シーングラフを作成
            StackPane Pane = new StackPane();

//            File files[] = new File
            System.out.println("file number : " + dir.listFiles().length);

            // 動画ファイルのパス
            //File f = new File( "C:\\Users\\ht_2\\Videos\\QUEEN\\leoSayer.mp4" );
            //Scanner scan = new Scanner(System.in);
            //System.out.println(dir.listFiles()[scan.nextInt()].getPath());

            //File f = new File( dir.listFiles()[scan.nextInt()].getPath() );


            // 動画再生クラスをインスタンス化
            //Media Video = new Media( f.toURI().toString() );
            //MediaPlayer Play = new MediaPlayer(Video);
            WebView Video = new WebView();

            Video.getEngine().loadContent(content_Url);
            //Video.getEngine().load("https://www.youtube.com/watch?v=lJGCpmoLXqE");

            //MediaView mediaView = new MediaView(Play);
            //System.out.println(Video.getDuration().toString());

            //動画幅
            //mediaView.setFitWidth(300);
            Video.setPrefWidth(400);

            // シーングラフに追加
            //Pane.getChildren().add(mediaView);
            Pane.getChildren().add(Video);


            // シーンを追加
            Scene   scene   = new Scene(Pane, 300, 168);

            // ステージ設定
            primaryStage.setTitle("VideoPlay");
            primaryStage.setScene(scene);
            primaryStage.show();
            //Play.play();
            //System.out.println(mediaView.);
            //scan.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
	}

    //ディレクトリならその中のファイル数も数える
	private static int count(File[] list) {
		int count=0;
		for (File f : list) {
			if (f.isDirectory()) {
				count(f.listFiles());
			} else if (f.isFile()) {
				count++;
			}
		}
		return count;
	}


}
