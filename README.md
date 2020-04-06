<h1 align="center"> MVP Testing </h1>
Model-View-Presenter is one of the famous architecture (next to MVVM, MVI). MVP separates the implementation of logic/data handling in presenter and keep the view clean without, responsible for only updating the UI. Model is a mere representation of data entities, manipulated by presenter.

Unit testing for MVP requires testing techniques to capture the data and handle the callbacks so this repository demonstrates the use of `mockito` framework to implement unit testing in MVP architecture.


# MVP and test
- Model View Presenter
- Repository pattern
- Unit testing
- Espresso UI testing
- RecyclerView, CardView, Customize adapter

![mvp demo](screenshots/demo.gif?raw=true "demo")

#### License
[MIT](LICENSE)