Ext.define('app.Application', {
    name: 'app',

    extend: 'Ext.app.Application',

    views: [
        'Login'
    ],

    controllers: [
        //
    ],

    stores: [
        // TODO: add stores here
    ],

    launch: function () {

        var loggedIn;

        loggedIn = localStorage.getItem("TutorialLoggedIn");

		if (loggedIn == false) {

         Ext.create('app.view.Login',{
            xtype: 'login'
        })
		
		}
},    
});

