# Project Reactor: Create Efficient Reactive Systems

**Project Reactor** is a fully non-blocking reactive programming foundation of the JVM.

## Reactor: Why and When.

### Foundation: Operating System Threading

To understand the problem that Reactor should solve, we first need some insights about thread
management by the operating system.
In a modern setup, we have a multicore processor:
One CPU socket with multiple cores accessing the same memory.
Thread needs also to be distinguished, as there are user-level and kernel level threads:
