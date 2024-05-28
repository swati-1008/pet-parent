import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SocialNetwork } from './components/SocialNetwork';
import { Provider } from 'react-redux';
import store from './redux/store';
import Authentication from './components/Authentication';

function App() {
  return (
    <Provider store={store}>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path='/auth' element={<Authentication />} />
            <Route path="/social" element={<SocialNetwork />} />
          </Routes>
        </BrowserRouter>
      </div>
    </Provider>
  );
}

export default App;
