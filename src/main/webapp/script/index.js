require(["requirejs/config"], function() {
    'use strict'
    require(["jquery", "cometd"], function($, cometd) {
        'use strict'

        var cometd = new cometd.CometD();

        cometd.configure({
            url: ctx + "/cometd",
            logLevel: "debug"
        });

        cometd.handshake();

        var _reportListener;
        cometd.addListener('/meta/handshake', function(message) {
            if (message.successful) {
                cometd.subscribe("/service/test", function(message) {
                    console.log(message);
                    console.log(12345678);
                });
            }
        });
    });
});