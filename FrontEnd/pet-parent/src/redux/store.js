import { configureStore } from '@reduxjs/toolkit';
import rootReducer from './reducers';
import localforage from 'localforage';
import { persistReducer, persistStore } from 'redux-persist';

const persistConfig = {
    key: 'root', 
    storage: localforage, 
    whitelist: ['auth'], 
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
    reducer: persistedReducer, 
});

const persistor = persistStore(store);

export { store, persistor };