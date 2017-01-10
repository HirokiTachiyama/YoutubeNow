package ytbwd;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;


public class GetData {

	/** Global instance properties filename. */
	  private static String PROPERTIES_FILENAME = "youtube.properties";

	  /** Global instance of the HTTP transport. */
	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	  /** Global instance of the max number of videos we want returned. */
	  private static final long NUMBER_OF_VIDEOS_RETURNED = 5;

	  /** Global instance of the max number of topics we want returned. */
	  private static final long NUMBER_OF_TOPICS_RETURNED = 5;



	public static void getData(String[] args) throws IOException {

		String apiKey = "AIzaSyCfQ7MYBTb3CifxXiDzYTfRi4gGvdjYe6Q";
		String queryTerm = "Queen";

		YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {

			}
		}).setApplicationName("Youtube my window").build();

		YouTube.Search.List search = youtube.search().list("id,snippet");
		search.setKey(apiKey);    // APIキーを設定
		search.setQ(queryTerm);   // 検索するキーワードを設定
		search.setType("video");  // 検索対象のTypeを設定。他にchannelとplaylistが設定できる
		search.setMaxResults((long) 5); // 取得するヒット数の最大値を設定
		search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		// 取得するフィールドを設定

		SearchListResponse searchResponse = search.execute();
		System.out.println(searchResponse.toPrettyString()); // レスポンスのJSONを表示

		List<SearchResult> searchResultList = searchResponse.getItems();
//		while (searchResultList.hasNext()) {

		for(int i=0; i < searchResultList.size(); i++) {
		    SearchResult singleVideo = searchResultList.get(i); //.next();
		    ResourceId rId = singleVideo.getId();
		    if (rId.getKind().equals("youtube#video")) {
		        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
		        System.out.println(" Video Id" + rId.getVideoId());
		        System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
		        System.out.println(" Thumbnail: " + thumbnail.getUrl());
		    }
		}
	}

}
