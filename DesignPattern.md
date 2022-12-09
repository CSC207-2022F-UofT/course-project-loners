# Design pattern
We want to decrease couplings and increase cohesion.
## Singleton Design Pattern
We modified DataSendControl.java so that it follows singleton design pattern. There were some occasions where an instance of DataSendContorl is invoked but only used once for one method. We created getInstance method and static final variable storing an instance of DataSendControl so that other classes do not have to generate an instance of DataSendControl.

## Iterator Design Pattern
## Observer Design Pattern
When we need to update a lot of objects at once, we can use observer interface to do this

## Strategy Design Pattern

## Dependency Injection Design Pattern

## Factory Design Pattern

## Facade Design Pattern
facade design pattern was used for likes controller
## Builder Design Pattern
