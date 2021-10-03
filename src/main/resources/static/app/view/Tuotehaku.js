/** Tuotetietojen haku. Hakee tuotetiedot ruudulle tuotekoodin perusteella. Kuvan hakeminen ei vielä toimi
 */
 
Ext.define('app.view.Tuotehaku', {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.tab.Panel',
        'Ext.layout.container.Border',
        'Ext.form.Panel',
    ],

	xtype: "viewport",
	padding: 50,
	items: [{
        region: 'north',		
        html: '<h1 class="x-panel-header">Tuotetietojen haku</h1>',
        border: false,
        margin: '10 10 10 10'
    }, {
		xtype: 'form',		//Lomake, jolla tietoja haetaan
		region: 'center',
		layout: 'hbox',
    	url: '/Tuotehaku',

        items: [{
				margin: 10,					//Kenttä johon tuotekoodi kirjoitetaan
                xtype: 'textfield',	
                fieldLabel: 'tuotekoodi',
                width: 300,
        		name: 'tuotekoodi',
        		inputType: 'tuotekoodi',
        		allowBlank: false
            },
            {
                xtype: 'button',			
                margin: 10,
                text: 'Hae',
                width: 100,
                handler : function(button) {	//handleri lähetysbuttoniin
		        var form = this.up('form'); 
                if (form.isValid()) { 		//tarkistetaan validius ennen lähettämistä
                    form.submit({
                        success: function(form, action) {		//Jos onnistuu niin tiedot ruudulle 
						
						  var viewport = button.up('viewport')
                          viewport.add({
						  xtype: 'panel',
						  width: '50%',
						  title: 'Tuotteen tiedot',
						  items: [{
							html: '		Tuotekoodi: ' + action.result.tuotekoodi, 
							height:40,
							fontsize: 30
							
						}, {
							layout:'fit',
							html: '		'+ action.result.tuotenimi, 
							height:40
							},{
							html: '		' + action.result.tuotepaino,	
							height:40
							},{
							html: '		' + action.result.energiamaara + action.result.energiamaaraJ,
							height:40
							}],
				          });
				          
				          
				         //Tähän tulee joskus kuvan haku:
				          /** 
				          Ext.Ajax.request({
						    url: '/Tuotekuva',
						    params: {
						        tuotekoodi: action.result.tuotekoodi
						    },
						    success: function(response){
						        var kuva = new Blob([response], {type: 'image/png'}),
    							url = window.URL.createObjectURL(kuva),
    							img = document.createElement('img');
   								img.src = url;
   								
								viewport.add( {
									
									xtype: 'panel',
									region: 'east',
									width: '50%',
									title: 'Kuva',
									items: [{
								        xtype: 'image',
								        src: img.src,
								    }]
								});
								
						    }
						});
				          
				          */
                                   
                        },
                        failure: function(form, action) {					//Jos tuotekoodilla ei löydy tuotteita, näytetään sen kertova ikkuna
                            Ext.Msg.alert('Failed', action.result.msg);
                        }
                    });
                } 
			}  
        }]  		
	}]
});
