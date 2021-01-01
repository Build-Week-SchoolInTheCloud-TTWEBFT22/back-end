# Build Week School In The Clouds - TT22

### POST - Create Account
create a user with the usertype student or volunteer
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/createnewuser/{usertype}</summary>

```JSON
[
    {
        "username": "testusername",
        "email": "email@email.com",
        "password": "password"
    }
]
```
</details>


### GET - Get All Volunteers
get all volunteers
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/volunteers/volunteers</summary>

```JSON
[
   {
        "userid": 15,
        "username": "laquita.greenfelder",
        "primaryemail": "emmanuel.bosco@yahoo.com",
        "country": "United Arab Emirates",
        "availability": "Sun Jan 17 21:54:56 UTC 2021",
        "usertasks": [
            {
                "taskid": 16,
                "description": "Teach Consulting"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 3,
                    "name": "VOLUNTEER"
                }
            }
        ]
    }
]
```
</details>

### GET - Get All Tasks
get all assigned tasks
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/tasks/tasks</summary>

```JSON
[
     {
        "taskid": 12,
        "description": "Teach Advertising",
        "user": {
            "userid": 11,
            "username": "belinda.ferry",
            "primaryemail": "mac.pfeffer@hotmail.com",
            "country": "Sweden",
            "availability": "Fri Jan 29 01:57:57 UTC 2021",
            "roles": [
                {
                    "role": {
                        "roleid": 3,
                        "name": "VOLUNTEER"
                    }
                }
            ]
        }
    }
]
```
</details>

