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

-----------------------------------------------------------------------------------------

### GET - Logout User
create a user with the usertype student or volunteer
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/logout</summary>

```JSON
HttpStatus OK
```
</details>

-----------------------------------------------------------------------------------------

### GET - Get All Volunteers
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

-----------------------------------------------------------------------------------------

### GET - Get All Tasks
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

-----------------------------------------------------------------------------------------

### GET - Get Specific Task
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/tasks/task/{taskid}</summary>

```JSON
[
    {
        "taskid": 10,
        "description": "Teach Music",
        "user": {
            "userid": 6,
            "username": "volunteer",
            "primaryemail": "volunteer@lambdaschool.local",
            "country": null,
            "availability": null,
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

-----------------------------------------------------------------------------------------

### DELETE - Delete A Specific Task
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/tasks/task/{taskid}</summary>

```JSON
HttpStatus OK
```
</details>


-----------------------------------------------------------------------------------------

### PUT - Update A Specific Task
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/tasks/task/{taskid}/description/{taskdescription}</summary>

```JSON
HttpStatus OK
```
</details>


-----------------------------------------------------------------------------------------

### POST - Add A Task To A Volunteer
<details>
<summary>https://schoolinthecloudstt22.herokuapp.com/tasks/task/{taskid}/description/{taskdescription}</summary>

```JSON
HttpStatus CREATED
```
</details>

