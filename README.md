# HotelAutomation

Night-Time

1. The default state

Input :
{
	"noOfFloors" : 2,
	"noOfMainCorridorsPerFloor" : 1,
	"noOfSubCorridorsPerFloor" : 2,
	"movement" : 
		[]
}

Output :
{
    "floorList": [
        {
            "floorNo": 1,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                }
            ]
        },
        {
            "floorNo": 2,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                }
            ]
        }
    ]
}

2. Movement in Floor 1, Sub corridor 2

Input :
{
	"noOfFloors" : 2,
	"noOfMainCorridorsPerFloor" : 1,
	"noOfSubCorridorsPerFloor" : 2,
	"movement" : 
		[{
			"isMovement" : true,
			"floorNo" : 1,
			"subCorridorNo" : 2
		}]
}

Output :
{
    "floorList": [
        {
            "floorNo": 1,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "OFF"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ]
        },
        {
            "floorNo": 2,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                }
            ]
        }
    ]
}


3. No movement in Floor 1, Sub corridor 2

Input :
{
	"noOfFloors" : 2,
	"noOfMainCorridorsPerFloor" : 1,
	"noOfSubCorridorsPerFloor" : 2,
	"movement" : 
		[{
			"isMovement" : false,
			"floorNo" : 1,
			"subCorridorNo" : 2
		}]
}

Output :
{
    "floorList": [
        {
            "floorNo": 1,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                }
            ]
        },
        {
            "floorNo": 2,
            "mainCorridorList": [
                {
                    "mainCorridorNo": 1,
                    "lightStatus": "ON",
                    "acStatus": "ON"
                }
            ],
            "subCorridorList": [
                {
                    "subCorridorNo": 1,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                },
                {
                    "subCorridorNo": 2,
                    "lightStatus": "OFF",
                    "acStatus": "ON"
                }
            ]
        }
    ]
}
