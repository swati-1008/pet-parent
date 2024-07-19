import React from 'react';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Authentication from './components/Authentication';
import Home from './components/Home';
import FurryFeeds from './components/FurryFeeds';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={
            isAuthenticated ? <Navigate to='/home' /> : <Navigate to='/auth' />
          } />
          <Route path='/auth' element={<Authentication />} />
          <Route path='/' element={<ProtectedRoute />}>
          <Route path='/furry-feeds' element={<FurryFeeds />} />
          <Route path='/home' element={<Home />} />
          <Route path='/retail-stores' element={<Home />} />
          <Route path='/chat-rooms' element={<Home />} />
          <Route path='/vet-consultations' element={<Home />} />
          <Route path='/blogs' element={<Home />} />
          <Route path='/events' element={<Home />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;


// TODO: 
// 1. Do unit testing in both BE and FE
// 3. Add Readme.MD