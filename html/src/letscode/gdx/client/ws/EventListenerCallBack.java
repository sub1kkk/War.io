package letscode.gdx.client.ws;


import jsinterop.annotations.JsFunction;

@JsFunction
public interface EventListenerCallBack {
    void callEvent(WsEvent event);
}
