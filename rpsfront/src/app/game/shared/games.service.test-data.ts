export const GAMES = {
  "_embedded": {
    "games": [
      {
        "id": "5ae2e358-95bb-4ff1-b17c-7e5c43d78a44",
        "rounds": [
          {
            "playerOneMove": "PAPER",
            "playerTwoMove": "PAPER",
            "randomPlayerTwo": true
          }
        ],
        "winner": "NONE",
        "status": "ONGOING",
        "startedOn": "2023-12-25T12:03:58.804684297",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44"
          }
        },
        "_templates": {
          "default": {
            "method": "PUT",
            "properties": []
          },
          "play-random-round-command": {
            "method": "POST",
            "properties": [
              {
                "name": "playerOneMove",
                "readOnly": true,
                "required": true,
                "options": {
                  "inline": [
                    "ROCK",
                    "PAPER",
                    "SCISSORS"
                  ]
                }
              }
            ],
            "target": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44/play/round/random/command"
          }
        }
      },
      {
        "id": "a9e8fa2e-74ef-4a26-a15f-36b2c4c8ab2c",
        "rounds": [
          {
            "playerOneMove": "PAPER",
            "playerTwoMove": "PAPER",
            "randomPlayerTwo": true
          }
        ],
        "winner": "NONE",
        "status": "ONGOING",
        "startedOn": "2023-12-25T12:03:58.805288774",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/games/a9e8fa2e-74ef-4a26-a15f-36b2c4c8ab2c"
          }
        },
        "_templates": {
          "default": {
            "method": "PUT",
            "properties": []
          },
          "play-random-round-command": {
            "method": "POST",
            "properties": [
              {
                "name": "playerOneMove",
                "readOnly": true,
                "required": true,
                "options": {
                  "inline": [
                    "ROCK",
                    "PAPER",
                    "SCISSORS"
                  ]
                }
              }
            ],
            "target": "http://localhost:8080/api/games/a9e8fa2e-74ef-4a26-a15f-36b2c4c8ab2c/play/round/random/command"
          }
        }
      },
      {
        "id": "fecdfa36-260d-4be5-9503-16f41737a410",
        "rounds": [],
        "winner": "NONE",
        "status": "ONGOING",
        "startedOn": "2023-12-25T12:03:58.805306649",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/games/fecdfa36-260d-4be5-9503-16f41737a410"
          }
        },
        "_templates": {
          "default": {
            "method": "PUT",
            "properties": []
          },
          "play-random-round-command": {
            "method": "POST",
            "properties": [
              {
                "name": "playerOneMove",
                "readOnly": true,
                "required": true,
                "options": {
                  "inline": [
                    "ROCK",
                    "PAPER",
                    "SCISSORS"
                  ]
                }
              }
            ],
            "target": "http://localhost:8080/api/games/fecdfa36-260d-4be5-9503-16f41737a410/play/round/random/command"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/games"
    }
  },
  "_templates": {
    "default": {
      "method": "PUT",
      "properties": []
    },
    "start-game-command": {
      "method": "POST",
      "properties": [],
      "target": "http://localhost:8080/api/games/start/game/command"
    }
  }
};

export const GAME = {
  "id": "5ae2e358-95bb-4ff1-b17c-7e5c43d78a44",
  "rounds": [
    {
      "playerOneMove": "PAPER",
      "playerTwoMove": "PAPER",
      "randomPlayerTwo": true
    }
  ],
  "winner": "NONE",
  "status": "ONGOING",
  "startedOn": "2023-12-25T12:03:58.804684297",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44"
    }
  },
  "_templates": {
    "default": {
      "method": "PUT",
      "properties": []
    },
    "play-random-round-command": {
      "method": "POST",
      "properties": [
        {
          "name": "playerOneMove",
          "readOnly": true,
          "required": true,
          "options": {
            "inline": [
              "ROCK",
              "PAPER",
              "SCISSORS"
            ]
          }
        }
      ],
      "target": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44/play/round/random/command"
    }
  }
};

export const FINISHED_GAME = {
  "id": "5ae2e358-95bb-4ff1-b17c-7e5c43d78a44",
  "rounds": [
    {
      "playerOneMove": "PAPER",
      "playerTwoMove": "ROCK",
      "randomPlayerTwo": true
    }
  ],
  "winner": "PLAYER1",
  "status": "FINISHED",
  "startedOn": "2023-12-25T12:03:58.804684297",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44"
    }
  },
  "_templates": {
    "default": {
      "method": "PUT",
      "properties": []
    },
    "play-random-round-command": {
      "method": "POST",
      "properties": [
        {
          "name": "playerOneMove",
          "readOnly": true,
          "required": true,
          "options": {
            "inline": [
              "ROCK",
              "PAPER",
              "SCISSORS"
            ]
          }
        }
      ],
      "target": "http://localhost:8080/api/games/5ae2e358-95bb-4ff1-b17c-7e5c43d78a44/play/round/random/command"
    }
  }
};
