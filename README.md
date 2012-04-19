# RT-REST

RT-REST is a Java class library to interact with RT: Request Tracker's REST Interface.

To see it in action simply open your terminal and execute:

```
git clone git@github.com:bboksa/RT-REST.git
mvn test
```

Using it in your own code is just as simple:

```
RTTicketRESTDAO dao = new RTTicketRESTDAO();
// see http://requesttracker.wikia.com/wiki/Demo
dao.setClient(new RTRESTClient("http://rt.easter-eggs.org/demos/stable/REST/1.0/", "john.foo", "john.foo"));
try {
	List<RTTicket> result = dao.findByQuery("Queue = 'Customer Service'", RTRESTClient.TicketSearchResponseFormat.MULTILINE);						
} catch (Exception ex) {
	ex.printStackTrace();
}
```

***

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/80x15.png" /></a><br />
<span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">RT-REST</span> by <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/bboksa/RT-REST" property="cc:attributionName" rel="cc:attributionURL">Benjamin Boksa</a> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.