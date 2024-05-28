import { styled } from '@mui/material/styles';

export const StyledBackground = styled('div')(() => ({
    position: 'absolute', 
    width: '100vw', 
    height: '100vh', 
    overflow: 'hidden', 
    backgroundColor: '#F2F2F2', 
}));

export const BackgroundImage = styled('img')(() => ({
    position: 'absolute', 
    right: 0, 
    bottom: 0, 
    width: '50%', 
}));

export const FormContainer = styled('div')(() => ({
    position: 'absolute', 
    top: '50%', 
    left: '50%', 
    transform: 'translate(-50%, -50%)', 
    width: '72%', 
    height: '75%', 
    display: 'flex', 
    border: '10px solid white', 
    borderRadius: '30px', 
}));

export const FormCard = styled('div')(() => ({
    position: 'relative', 
    width: '50%', 
    height: '100%', 
    display: 'flex', 
    flexDirection: 'column', 
    justifyContent: 'center', 
    alignItems: 'center', 
    backgroundColor: 'white',
}));

export const Form = styled('form')(() => ({
    width: '100%', 
    maxWidth: '400px', 
    display: 'flex', 
    flexDirection: 'column', 
    gap: '20px', 
}));

export const PawPrintContainer = styled('div')(() => ({
    position: 'absolute', 
    width: '50%', 
    height: '100%', 
}));

export const PawPrint = styled('img')(({ size, top, left }) => ({
    position: 'absolute', 
    width: size || '50px', 
    top: top || '50%', 
    left: left || '50%', 
    transform: 'scaleX(-1)', 
}));

export const LinkText = styled('p')(() => ({
    marginTop: '20px', 
    textAlign: 'center', 
}));

export const Link = styled('span')(() => ({
    cursor: 'pointer', 
    color: '#0066CC', 
}));