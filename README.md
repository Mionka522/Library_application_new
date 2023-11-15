# Library_application_new
Измененное приложение Library_application (замена JdbcTemplate на Spring Data)
Версия приложения с JdbcTemplate - https://github.com/Mionka522/Library_application.git 
Spring



Веб-приложение для библеотеки.
Библиотекари имеют возможность регистрировать читателей, выдавать им
книги и освобождать книги (после того, как читатель возвращает
книгу обратно в библиотеку).


Отношение между сущностями: Один ко Многим.
У человека может быть множество книг. Книга может принадлежать
только одному человеку.


Функционал:
1) Страницы добавления, изменения и удаления человека.
2) Страницы добавления, изменения и удаления книги
3) Страница со списком всех людей (люди кликабельные - при клике осуществляется
переход на страницу человека).
4) Страница со списком всех книг (книги кликабельные - при клике осуществляется
переход на страницу книги).
5) Страница человека, на которой показаны значения его полей и список книг, которые он
взял (книги кликабельные - при клике осуществляется
переход на страницу книги). Если человек не взял ни одной книги, вместо списка должен быть текст "Человек
пока не взял ни одной книги".
6) Страница книги, на которой показаны значения полей этой книги и имя человека,
который взял эту книгу. Если эта книга не была никем взята, должен быть текст "Эта
книга свободна".
7) На странице книги, если книга взята человеком, рядом с его именем должна быть кнопка
"Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель
возвращает эту книгу обратно в библиотеку. После нажатия на эту кнопку книга снова
становится свободно и пропадает из списка книг человека.
8) На странице книги, если книга свободна, должен быть выпадающий список (<select>)
со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем
тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга
должна начать принадлежать выбранному человеку и должна появится в его списке
книг.
9) Все поля должны валидироваться - с помощью @Valid и Spring Validator, если это
требуется.
