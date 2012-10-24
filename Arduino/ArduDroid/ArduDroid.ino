#include "SPI.h"
#include "Ethernet.h"
#include "WebServer.h"

/* CHANGE THIS TO YOUR OWN UNIQUE VALUE.  The MAC number should be
 * different from any other devices on your network or you'll have
 * problems receiving packets. */
static uint8_t mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

/* CHANGE THIS TO MATCH YOUR HOST NETWORK.  Most home networks are in
 * the 192.168.0.XXX or 192.168.1.XXX subrange.  Pick an address
 * that's not in use and isn't going to be automatically allocated by
 * DHCP from your router. */
static uint8_t ip[] = { 192, 168, 1, 252 };
int estLED1 = 0;
int estLED2 = 0;

int ArQuente = 3;
int ArFrio = 4;
int LuzSala = 2;
int LuzQuarto = 5;
int histerese = 3;
boolean aquecendo = false;
boolean esfriando = false;

int pinoSensor = 0;
int valorLido = 0; //valor lido na entrada analogica
float temperatura = 0; //valorLido convertido para temperatura
float tempdesj = 21;

/* all URLs on this server will start with /buzz because of how we
 * define the PREFIX value.  We also will listen on port 80, the
 * standard HTTP service port */
#define PREFIX ""
WebServer webserver(PREFIX, 80);

void apagarLed1 ()
{
  analogWrite(LuzQuarto, LOW);
  estLED1=0;
}

void acenderLed1 ()
{
  digitalWrite(LuzQuarto, HIGH);
  estLED1=1;
}

void apagarLed2 ()
{
  digitalWrite(LuzSala, LOW);
  estLED2=0;
}

void acenderLed2 ()
{
  digitalWrite(LuzSala, HIGH);
  estLED2=1;
}

void LigarArFrio()
{
  digitalWrite(ArFrio, HIGH);
}

void DesligarArFrio()
{
  digitalWrite(ArFrio, LOW);
}

void LigarArQuente()
{
  digitalWrite(ArQuente, HIGH);
}

void DesligarArQuente()
{
  digitalWrite(ArQuente, LOW);
}

/* This command is set as the default command for the server.  It
 * handles both GET and POST requests.  For a GET, it returns a simple
 * page with some buttons.  For a POST, it saves the value posted to
 * the buzzDelay variable, affecting the output of the speaker */
void cmd(WebServer &server, WebServer::ConnectionType type, char *url_tail, bool tail_complete)
{
  if (type == WebServer::POST)
  {
    Serial.println("Post Received");
    bool repeat;
    char name[8], value[8];
    /* readPOSTparam returns false when there are no more parameters
     * to read from the input.  We pass in buffers for it to store
     * the name and value strings along with the length of those
     * buffers. */
    repeat = server.readPOSTparam(name, 8 , value, 8);
    Serial.print("Name: ");
    Serial.println(name);
    Serial.print("Repeat: ");
    Serial.println(repeat);
    Serial.print("Valor: ");
    Serial.println(value);
    /* this is a standard string comparison function.  It returns 0
     * when there's an exact match. */
    if (strcmp(name, "led1") == 0)
    {
	/* use the STRing TO Unsigned Long function to turn the string
	 * version of the delay number into our integer buzzDelay
	 * variable */
      if (strcmp(value, "1") == 0)
      {
        acenderLed1();
        Serial.println("Acender Led1");
      }
      if (strcmp(value, "0") == 0)
      {
        apagarLed1();
        Serial.println("Apagar Led1");
      }
    }
    if (strcmp(name, "led2") == 0)
    {
	/* use the STRing TO Unsigned Long function to turn the string
	 * version of the delay number into our integer buzzDelay
	 * variable */
      if (strcmp(value, "1") == 0)
      {
        acenderLed2();
        Serial.println("Acender Led2");
      }
      if (strcmp(value, "0") == 0)
      {
        apagarLed2();
        Serial.println("Apagar Led2");
      }
    }
    if (strcmp(name, "temp") == 0)
    {
	/* use the STRing TO Unsigned Long function to turn the string
	 * version of the delay number into our integer buzzDelay
	 * variable */
      tempdesj = atof(value);
    }
    server.httpSeeOther(PREFIX);
    return;
  }else{

    /* for a GET or HEAD, send the standard "it's all OK headers" */
    server.httpSuccess("application/json");
    //server.httpSuccess();
  
    /* we don't output the body for a HEAD request */
    if (type == WebServer::GET)
    {
      Serial.println("GET Received");
      /* store the HTML in program memory using the P macro */
      //server.println("HTTP/1.1 200 OK");
      //server.println("Content-Type: application/json");
      //    server.println();
      // output the value of each analog input pin as a json-p object
      server.print("{\"arduino\":[");
      server.print("{");
      server.print("\"temperatura\"");
      server.print(": \"");
      server.print((int)temperatura);
      server.print("\"");
      server.print(",");
      server.print("\"led1\"");
      server.print(": \"");
      server.print(estLED1);
      server.print("\"");
      server.print(",");
      server.print("\"led2\"");
      server.print(": \"");
      server.print(estLED2);
      server.print("\"");
      server.print(",");
      server.print("\"tempd\"");
      server.print(": \"");
      server.print((int)tempdesj);
      server.print("\"");
      server.println("}]}");
    }
  }
}

void setup()
{
  pinMode(ArQuente, OUTPUT);
  pinMode(ArFrio, OUTPUT);
  pinMode(LuzSala, OUTPUT);
  pinMode(LuzQuarto, OUTPUT);
  Serial.begin(9600);
  Serial.println("Serial Init");

  // setup the Ehternet library to talk to the Wiznet board
  Ethernet.begin(mac, ip);
  Serial.println("Ethernet Init");
  /* register our default command (activated with the request of
   * http://x.x.x.x/ */
  webserver.setDefaultCommand(&cmd);

  /* start the server to wait for connections */
  webserver.begin();
  Serial.println("Webserver Init");
}

void loop()
{
  delay(200);
  int i = 0;
  int jTemp = 0;
  Serial.print("Temperatura atual: ");
  for(i;i<10;i++){

    valorLido = analogRead(pinoSensor);
    temperatura = (valorLido * 0.00488);  // 5V / 1023 = 0.00488 (precisão do A/D)
    temperatura = temperatura * 100; //Converte milivolts para graus celcius, lembrando que a cada 10mV equivalem a 1 grau celcius
    jTemp = jTemp + temperatura;
  }
  temperatura = jTemp/10;
  Serial.print((int)tempdesj);
  Serial.print(" = ");
  Serial.println((int)temperatura);
  Serial.print(aquecendo);
  Serial.print(" = ");
  Serial.println(esfriando);
  if (((int)temperatura) < tempdesj)
  {
    if (aquecendo == false) {
      DesligarArFrio();
      LigarArQuente();
      aquecendo = true;
      esfriando = false;
    } else {
      if ((int)temperatura > (tempdesj + histerese))
      {
        DesligarArQuente();
        aquecendo = false;
      }
    }
  } else if ((int)temperatura > tempdesj)
    {
      if (!esfriando)
      {
        DesligarArQuente();
        LigarArFrio();
        esfriando = true;
        aquecendo = false;
      } else {
        if ((int)temperatura < (tempdesj - histerese))
        {
          DesligarArFrio();
          esfriando = false;
        }
      }
    }
  /*else if (tempdesj < ((int)temperatura-3))
  {
    LigarArFrio();
    DesligarArQuente();
  }*/
/*  else if (tempdesj >= ((int)temperatura-3) && tempdesj <=((int)temperatura+3))
  {
    DesligarArFrio();
    DesligarArQuente();
  }*/
  // process incoming connections one at a time forever
  webserver.processConnection();
}
