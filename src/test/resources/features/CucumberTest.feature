# language: ru
@all
@add
Функция: Добавление товара



  @correct
  Сценарий: Успешное добавление товара - сценарий 1
    * нажать на кнопку добавить
    * проверка, что значение в поле ввода равно "Картофель"
    * выбор типа товара "Овощ"
    * проверка, что в списке выбрано нужное значение "VEGETABLE"
    * нажать на кнопку сохранить


  @correct
  Сценарий: Успешное добавление товара - сценарий 2
    * нажать на кнопку добавить
    * проверка, что значение в поле ввода равно "Мандарин"
    * выбор типа товара "Фрукт"
    * проверка, что в списке выбрано нужное значение "FRUIT"
    * нажать на кнопку сохранить
