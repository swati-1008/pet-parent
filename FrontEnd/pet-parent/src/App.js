import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SocialNetwork } from './components/SocialNetwork';
import { Provider } from 'react-redux';
import store from './redux/store';
import Authentication from './components/Authentication';
import Home from './components/Home';

function App() {
  return (
    <Provider store={store}>
      <div>
        <BrowserRouter>
          <Routes>
            <Route path='/auth' element={<Authentication />} />
            <Route path="/social" element={<SocialNetwork />} />
            <Route path='/' element={<Home />} />
            <Route path='/retail-stores' element={<Home />} />
            <Route path='/chat-rooms' element={<Home />} />
            <Route path='/vet-consultations' element={<Home />} />
            <Route path='/blogs' element={<Home />} />
            <Route path='/events' element={<Home />} />
          </Routes>
        </BrowserRouter>
      </div>
    </Provider>
  );
}

export default App;
