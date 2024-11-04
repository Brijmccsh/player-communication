# Player Communication System

This project implements a communication system between two players using pure Java. It demonstrates both single-process and multi-process implementations.

## Requirements Fulfilled

1. Player Class: Implemented a `Player` class that can communicate with other `Player` instances.

2. Two Players: The system creates and manages two player instances.

3. Initiator: One player (the "initiator") starts the communication by sending the first message.

4. Message Exchange: When a player receives a message, it sends back a new message containing the received message concatenated with its own message counter.

5. Graceful Termination: The program finalizes after the initiator has sent and received 10 messages.

6. Single Process Implementation: Both players run in the same Java process (see `run.sh`).

7. Multi-Process Implementation: An alternative implementation where each player runs in a separate Java process (see `run_separate_processes.sh`).

## Class Responsibilities

- `Player`: Manages individual player behavior, message sending, and receiving.
- `Message`: Represents a message in the communication system.
- `Communication`: Orchestrates the communication between players in the single-process version.
- `Main`: Entry point for the single-process version.
- `PlayerProcess`: Manages player behavior in the multi-process version.

## Technology

The project uses pure Java without additional frameworks, focusing on a clean and simple design.

## Running the Program

## Single Process Version
./run.sh


## Multi-Process Version
./multi-run.sh



## Project Structure

This is a Maven project containing only source code (no JARs). The project structure is as follows:



## Design Focus

The implementation prioritizes a clean and clear design while maintaining full functionality. It demonstrates flexibility by providing both single-process and multi-process implementations using the simplest possible technology to achieve the target.