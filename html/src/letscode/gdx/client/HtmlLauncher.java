package letscode.gdx.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import letscode.gdx.Starter;
import letscode.gdx.client.ws.EventListenerCallBack;
import letscode.gdx.client.ws.WebSocket;

import java.util.concurrent.atomic.AtomicBoolean;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                return new GwtApplicationConfiguration(true);
                // Fixed size application:
                //return new GwtApplicationConfiguration(480, 320);
        }
        private native WebSocket getWebSocket(String url)
                /*-{
                return new WebSocket(url);
                }-*/
        ;

        private native void log(Object obj)
                /*-{
                    console.log(obj);
                }-*/
        ;

        @Override
        public ApplicationListener createApplicationListener () {
                WebSocket client = getWebSocket("ws://localhost:8888/ws");
                AtomicBoolean once = new AtomicBoolean(false);

                Starter starter = new Starter();

                EventListenerCallBack callBack = event -> {
                        if(!once.get()){
                                client.send("hello");
                                once.set(true);
                        }
                        log(event.getData());
                };


                client.addEventListener("open", callBack);
                client.addEventListener("close", callBack);
                client.addEventListener("error", callBack);
                client.addEventListener("message", callBack);

                return starter;
        }
}