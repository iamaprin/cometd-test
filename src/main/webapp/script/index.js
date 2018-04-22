require(['requirejs/config'], function() {
'use strict'
require(['jquery', 'cometd'], function($, cometd) {
    'use strict'

    var cometd = new cometd.CometD('test');
    var channel = '/room/1';
    
    console.log(ctx);
    
    cometd.websocketEnabled = true;
    cometd.init({
        url: location.protocol + '//' + location.host + ctx + '/cometd',
        logLevel: 'debug'
    });
    
    cometd.addListener('/meta/handshake', function(message) {
        if (message.successful) {
            console.log('handshake success.');

            cometd.subscribe(channel, function(message) {
                console.log(message);
            }, function(subscribeReply) {
                console.log(subscribeReply);
            });

            cometd.subscribe('/**', function(message) {
                console.log(arguments);
                $('.on-message').prepend($('<li>').text(JSON.stringify(message)));
            });
        }
    });
    
    cometd.addListener('/meta/connect', function(message) {
        console.log(message);
    });

    $(() => {
        onSubmit();
    })

    function onSubmit() {
        $('#submit').on('click', (e) => {
            $.post(ctx + '/submit', $('#form').serialize(), (resp) => {
                console.log(resp);
            });
        })
    }

    

});
});