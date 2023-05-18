import { configureStore } from "@reduxjs/toolkit";
import ToDoSlice from "./ToDoSlice";

export const store = configureStore({
  reducer: {
    ToDoList: ToDoSlice,
    //này là cái để dùng cho dòng const ToDoList = useSelector((state) => state.ToDoList.value); bên file ToDoApp.js
  },
});