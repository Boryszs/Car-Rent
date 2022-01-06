# Car-Rental

###### Instrukcja Uruchomienia Projektu:
- git clone https://github.com/Boryszs/Car-Rent.git

###### Instrukcja Uruchomienia Testów:
- mvn integration-test

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
- itext
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
GET 
​/user​/get?id = id
```  

```sh
DELETE 
​/user​/delete?id = id
```  

##### reservation-controller
```sh
DELETE
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
POST
​/reservation​/add-pdf
{
  "id_user": 0,
  "id_car": 0,
  "dateto": "string",
  "datefrom": "string",
  "localization_end": "string",
  "localization_start": "string"
}
Response : Resume order pdf.

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
​/reservation​/get-all-user?id = id  
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

Response  
{
    "token": "string",
    "type": "string",
    "id": 0,
    "username": "string",
    "email": "string",
    "localizations": [
        {
            "id": 0,
            "city": "string"
        }
    ],
    "roles": [
        "string",
        "string"
    ]
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

{
    "id": 0,
    "city": "string"
}
```  

```sh  
GET
​/city​/show-cit?city = city
{
    "id": 0,
    "city": "string"
}
```  

```sh  
GET
​/city​/show-all
[
  {
    "id": 0,
    "city": "string"
  },
  {
    "id": 0,
    "city": "string"
  }
]
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
PUT
​/car​/edit-car?id = id
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
DELETE
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
  [
   {
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
    {
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
    
  ]
```   

```sh  
GET
​/car​/get-car?id = id
    {
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
```     

```sh  
GET
​/car​/get-car-localization?city = city  
  [
   {
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
    {
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
    
  ]
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












