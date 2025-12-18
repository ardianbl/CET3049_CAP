import { createContext, useContext, useState } from "react";

const SnowContext = createContext();

export const SnowProvider = ({ children }) => {
    const [snowing, setSnowing] = useState(false);

    const toggleSnow = () => setSnowing(prev => !prev);

    return (
        <SnowContext.Provider value={{ snowing, toggleSnow }}>
            {children}
        </SnowContext.Provider>
    );
};

export const useSnow = () => useContext(SnowContext);
