package com.xcoder.serialport.server;


import android.util.Log;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class IotServer extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);

        Router router = Router.router(vertx);

        router.route(HttpMethod.POST, "/iot/test").method(HttpMethod.GET)
                .handler(ctx -> {
                    ctx.response().end("Hello Iot!");
                    Log.d("Server", "response success");
                });

//        router.route(HttpMethod.GET, "/html/:page").handler(ctx -> {
//            ctx.response().putHeader("content-type", "text/html")
//                    .sendFile(ctx.pathParam("page"));
//        });

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router).listen(8080);

        Log.d("Http Server", "Startup...");
    }
}
