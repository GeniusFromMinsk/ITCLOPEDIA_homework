public enum MenuAction {
    ADD_PET_HOUSE("Добавить новый питомник"),
    ADD_CAT_TO_HOUSE("Добавить кота в питомник"),
    REMOVE_CAT_FROM_HOUSE("Удалить кота из питомника"),
    TRANSFER_CAT("Перенести кота из одного питомника в другой"),
    VIEW_CATS_INFO("Просмотр информации о котах в питомнике"),
    VIEW_HOUSE_INFO("Просмотр информации о питомнике"),
    VIEW_CAT_INFO("Просмотр информации о конкретном коте"),
    EDIT_CAT_INFO("Редактировать информацию о коте"),
    EDIT_HOUSE_INFO("Редактировать информацию о питомнике"),
    DELETE_PET_HOUSE("Удалить питомник с переносом котов"),
    EXIT("Выход из программы");

    private final String description;

    MenuAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
