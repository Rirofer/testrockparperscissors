# Rock, paper, scissors test

Application composed of backend and frontend that implements the Rock, Paper, Scissors game.

## Use cases

The application implements the following use cases:

- Start game: The user can start a new game
- Play random round: The user can send its move (ROCK, PAPER, SCISSORS) and the player two move will be generated randomly
- View all games: The user can view all the games
- View the current game: The user can view the ongoing game

## Run the application

In order to run the application, execute the backend and the frontend as explained in their respective README files.

Open a browser and access the url http://localhost:4200, start a new game and play rounds until the game is finished.

## Architecture decisions

Although it is a simple application, the following architectural patterns have been used for the sake of demonstration.

- Domain Driven Design
- Hexagonal architecture
- REST API
- Angular Style Guide

## Improvements

- Add persistence to the backend
- Implement an EventStore to save domain events
- Implement Outbox pattern to publish events reliably
- Use Server Sent Events (or WebSockets) to send events from the backend to the frontend
- React to domain events in the frontend, for example, refresh the game list on GameFinished event
