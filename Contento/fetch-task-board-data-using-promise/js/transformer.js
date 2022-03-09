//filter
// export const getListsByBoardId = (data,boardId)=>{
//     let filteredLists = data.lists.filter(list=> list.boardId === boardId)
//     return transformList(data, filteredLists);
// }

//map
// export const transformList = (data, lists)=>{
//     return lists.map(list => {
//         return {
//             "listId": list.listId,
//             "listTitle": list.listTitle,
//             "cards": getCardsByListId(data,list.listId)
//         }
//     })
// }

//filter
// export const getCardsByListId = (data,listId)=>{
//     let filteredCards = data.cards.filter(card => card.listId === listId)
//     return transformCard(data,filteredCards)
// }

//map
// export const transformCard = (data, cards)=>{
//     return cards.map(card => {
//         return {
//         "cardId": card.cardId,
//         "cardTitle": card.cardTitle,
//         "comments": getCountOfCommentsByCardId(data.comments,card.cardId)
//     }})
// }

//reduce
// export const getCountOfCommentsByCardId = (comments, cardId)=>{
//     return comments.reduce ((acc,val)=>acc+1,0)
// }

//use above functions in transform() and get the required output


// export const transform = (data) => {
//     return data.boards.map(board=>{
//         return{
//             "boardId":board.boardId,
//             "boardTitle": board.boardTitle,
//             "lists": getListsByBoardId(data, board.boardId)
        
//         }
//     })
// }

export const transform = (data) => {
    //1. start with boards property of the data provided
    //2. transform the board data to contain boardId, boardTitle and lists

    return data.boards.map(board => {
        return {
            // set boardId
            "boardId": board.boardId,
            // set boardTitle
            "boardTitle": board.boardTitle,
            //3. use the lists property of the data provided 
            "lists": data.lists
                //4. filter lists by boardId
                .filter(list => list.boardId === board.boardId)
                //5. transform the list data to contain listId, listTitle and cards
                .map(list => {
                    return {
                        // set listId
                        "listId": list.listId,
                        // set listtitle
                        "listTitle": list.listTitle,
                        //6. use the cards property of the data provided
                        "cards": data.cards
                            //7. filter cards by listId
                            .filter(card => card.listId === list.listId)
                            //8. transform the card data to contain cardId, cardTitle and count of comments
                            .map(card => {
                                return {
                                    // set cardId
                                    "cardId": card.cardId,
                                    // set cardTitle
                                    "cardTitle": card.cardTitle,
                                    //9. use the comments property of the data provided
                                    "comments": data.comments
                                        //10. fitler comments by cardId
                                        .filter(comment => comment.cardId === card.cardId)
                                        // to get the count of the comments perform aggregation
                                        .reduce((prev, curr) => prev+1, 0)
                                }
                            })
                    }
                })
        }
    });
};