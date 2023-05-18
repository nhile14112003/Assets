import { createSlice } from "@reduxjs/toolkit";
import { act } from "react-test-renderer";

const initialState = {
  value: [
    {Title: 'ToDo1', Description: "Clean house", Time:'1/1/2023', MarkAsDone: false},
    {Title: 'ToDo2', Description: "Submit report of DSA", Time:'1/1/2023', MarkAsDone: false},
    {Title: 'ToDo3', Description: "Clean bathroom", Time:'1/1/2023', MarkAsDone: false}
  ],
};

export const ToDoSlice = createSlice({
  name: "ToDo",
  initialState,
  reducers: {
    Add: {
      reducer: (state, action) => {
        state.value = [...state.value, action.payload]
      },
      prepare: (Title, Description, Time, MarkAsDone) => {
        return { payload: {Title: Title, Description: Description, Time: Time, MarkAsDone: MarkAsDone } }
      }
    },
    Edit: {
      reducer: (state, action) => {
        for (let i = 0; i < state.value.length; i++){
          if (i == action.payload.index) {
            state.value[i].Title = action.payload.Title;
            state.value[i].Description= action.payload.Description;
            state.value[i].Time = action.payload.Time;
            state.value[i].MarkAsDone= action.payload.MarkAsDone;
        }
      }
          
      },
      prepare: (index, Title, Description, Time, MarkAsDone) => {
        return { payload: {index,  Title, Description, Time, MarkAsDone} }
      }
    },
    ChangeCheckBoxValue: {
      reducer: (state, action) => {
        for (let i = 0; i < state.value.length; i++){
          if (i == action.payload.index) {
            
            state.value[i].MarkAsDone= !action.payload.MarkAsDone;
        }
      }
          
      },
      prepare: (index, MarkAsDone) => {
        return { payload: {index, MarkAsDone} }
      }
    },
    Delete: {
      reducer: (state, action) => {
        let arr = [];
        for(let i = 0; i < state.value.length; i++){
            if (i != action.payload.index){
              arr.push(state.value[i]);
            }
        }
        state.value = arr;
      },
      prepare: (index) => {
        return { payload: {index} }
      }
    }
    
  },
});

export const { Add, Edit, Delete, ChangeCheckBoxValue} = ToDoSlice.actions;

export default ToDoSlice.reducer;