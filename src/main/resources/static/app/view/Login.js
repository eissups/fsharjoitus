/**Kirjautumissivu. Palaa tähän kun sivu päivitetään, sillä tietoa kirjautumisesta ei vielä tallenneta mihinkään
 */
Ext.define('app.view.Login', {
    extend: 'Ext.window.Window',
    requires:[
        'Ext.tab.Panel',
        'Ext.layout.container.Border',
        'Ext.form.Panel'

    ],
    xtype: 'login',	
    title: 'Kirjaudu sisään',
	closable: false,
    frame: 'true',
    width: 340,
    autoShow: true,
    params: {
        newStatus: 'delivered'
    },
    url: '/Login',
	type: 'remoting',
    items: {			//Lomake ja siihen kentät joihin käytttäjä syöttää käyttäjänimen ja salasanan
        xtype: 'form',
        reference: 'form',
        url: '/Login',
        items: [{
            xtype: 'textfield',
            name: 'username',
            fieldLabel: 'käyttäjänimi',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'password',
            inputType: 'password',
            fieldLabel: 'salasana',
            allowBlank: false
        }],
        buttons: [
            {
		   text: 'Kirjaudu',
           handler: function(button) {		//buttoniin handleri, Jos painetaan niin:
                var form = this.up('form'); 
                if (form.isValid()) {		//Tarkistetaan tiedot ennen lähettämistä
                    form.submit({			//Ja ähetetään lomake
                        success: function(form, action) {
                           button.up('login').close();       	//Jos onnistuu siirrytään tuotehakuun            
                           Ext.create('app.view.Tuotehaku',{
            					xtype: 'viewport'
        				    })
                        },
                        failure: function(form, action) {
                            Ext.Msg.alert('Failed', action.result.msg);		//Jos epäonnistuu näytetään ikkuna "Väärä käyttäjänimi tai salasana"
                        }
                    });
                } else {
                    Ext.Msg.alert('Invalid Data') //Jos tarkistus ei mene läpi
                }
           	  }	        
            }
        ]
    }
});