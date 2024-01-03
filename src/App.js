import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SocialNetwork } from './components/SocialNetwork';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/social" element={<SocialNetwork />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
