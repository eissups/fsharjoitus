Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.layout.container.Fit',
		'app.view.Login'

    ],

    layout: {
        type: 'fit'
    },

    items: [{
        xtype: 'login'
    }
	]
});


