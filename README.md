# Car-Rental  
##### Projekt wykonany w ramach zajęć Podstawy Programowania Systemów Mobilnych II  
###### Język Programowania:  
- Java wersja 15.  
###### Użyte technologie:
- Spring Boot  
- Spring Security  
- Hibernate JPA  
- Spring Data   
- Javax Mail  
- Hibernate  
- JSON Web Token  
##### Dokumentacja Servera:  
[Dokumentacja Servera](https://boryszs.github.io/Car-Rent/)
#### Dokumentacja API:    
##### user-controller
```sh
POST 
​/user​/edit  
    {
    "id": 0,
    "username": "string",
    "email": "string",
    "role": [
    "string"
    ],
    "password": "string"
    }
```  

```sh
POST 
​/user​/delete?id = id
```  

##### reservation-controller
```sh
POST
​/reservation​/delete?id = id
```  

```sh
POST
​/reservation​/add
{
  "id_user": 0,
  "id_car": 0,
  "dateto": "string",
  "datefrom": "string",
  "localization_end": "string",
  "localization_start": "string"
}
```  

```sh
GET
​/reservation​/show
[
  {
    "idrent": 0,
    "car": {
      "idcar": 0,
      "mark": "string",
      "model": "string",
      "type": "string",
      "yearProduction": 0,
      "color": "string",
      "engineCapacity": 0,
      "money": 0,
      "image": "string",
      "localization": {
        "id": 0,
        "city": "string"
      }
    },
    "dataFrom": "2020-12-30T02:34:34.327Z",
    "dataTo": "2020-12-30T02:34:34.327Z",
    "localizationStart": {
      "id": 0,
      "city": "string"
    },
    "localizationEnd": {
      "id": 0,
      "city": "string"
    },
    "price": 0
  }
]
```   

```sh
GET
​/reservation​/get?id = id
```   

```sh
GET
​/reservation​/get-all-user?id = id
```  
  
##### authentication-controller  
  
```sh
POST 
​/register
{
  "username": "string",
  "email": "string",
  "role": [
    "string"
  ],
  "password": "string"
}
```  

```sh
POST 
​/authentication
{
  "username": "string",
  "password": "string"
}
```  

##### localization-controller
```sh
POST 
​/city​/add
{
  "city": "string"
}
```  

```sh
GET 
​/city​/show-id?id = id 
```  

```sh  
GET
​/city​/show-cit?city = city
```  

```sh  
GET
​/city​/show-all
```   

##### car-controller
```sh  
POST
​/car​/get-cars  
{
  "city": "string",
  "dateFrom": "string",
  "dateTo": "string"
}
``` 

```sh  
POST
​/car​/edit-car
{
  "idcar": 0,
  "mark": "string",
  "model": "string",
  "type": "string",
  "yearProduction": 0,
  "color": "string",
  "engine": 0,
  "city": "string",
  "money": 0,
  "image": "string"
}
```  

```sh  
POST
​/car​/delete-car?id = id
```   

```sh  
POST
​/car​/addcar  
{
  "mark": "string",
  "model": "string",
  "type": "string",
  "yearProduction": 0,
  "color": "string",
  "engine": 0,
  "city": "string",
  "money": 0,
  "image": "string"
}
```  

```sh  
GET
​/car​/show-car-all
```   

```sh  
GET
​/car​/get-car?id = id
```     

```sh  
GET
​/car​/get-car-localization?city = city
```   

##### test-controller  

```sh  
GET
​/test​/user  
{
  "message": "string"
}
```   

```sh  
GET
​/test​/all
string

```  

```sh  
GET
​/test​/admin
{
  "message": "string"
}
```  

##### admin-controller   
```sh  
GET
​/admin​/test  
"string"
```  

```sh  
GET
​/admin​/about  
"string"
```  












